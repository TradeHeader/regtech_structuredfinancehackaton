package cdm.product.asset;

import cdm.base.staticdata.asset.common.IndexReferenceInformation;
import cdm.base.staticdata.asset.common.IndexReferenceInformation.IndexReferenceInformationBuilder;
import cdm.base.staticdata.asset.common.IndexReferenceInformation.IndexReferenceInformationBuilderImpl;
import cdm.base.staticdata.asset.common.IndexReferenceInformation.IndexReferenceInformationImpl;
import cdm.product.asset.CreditIndexReferenceInformation;
import cdm.product.asset.CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder;
import cdm.product.asset.CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilderImpl;
import cdm.product.asset.CreditIndexReferenceInformation.CreditIndexReferenceInformationImpl;
import cdm.product.asset.CreditSeniorityEnum;
import cdm.product.asset.IndexAnnexSourceEnum;
import cdm.product.asset.ReferenceInformation;
import cdm.product.asset.SettledEntityMatrix;
import cdm.product.asset.Tranche;
import cdm.product.asset.meta.CreditIndexReferenceInformationMeta;
import cdm.product.asset.metafields.FieldWithMetaIndexAnnexSourceEnum;
import cdm.product.asset.metafields.FieldWithMetaIndexAnnexSourceEnum.FieldWithMetaIndexAnnexSourceEnumBuilder;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class defining a Credit Default Swap Index.
 * @version ${project.version}
 */
@RosettaDataType(value="CreditIndexReferenceInformation", builder=CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilderImpl.class, version="${project.version}")
public interface CreditIndexReferenceInformation extends IndexReferenceInformation, GlobalKey {

	CreditIndexReferenceInformationMeta metaData = new CreditIndexReferenceInformationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A CDS index series identifier, e.g. 1, 2, 3 etc.
	 */
	Integer getIndexSeries();
	/**
	 * A CDS index series version identifier, e.g. 1, 2, 3 etc.
	 */
	Integer getIndexAnnexVersion();
	/**
	 * A CDS index series annex date.
	 */
	Date getIndexAnnexDate();
	/**
	 * A CDS index series annex source.
	 */
	FieldWithMetaIndexAnnexSourceEnum getIndexAnnexSource();
	/**
	 * Excluded reference entity.
	 */
	List<? extends ReferenceInformation> getExcludedReferenceEntity();
	/**
	 * This element contains CDS tranche terms.
	 */
	Tranche getTranche();
	/**
	 * Used to specify the Relevant Settled Entity Matrix when there are settled entities at the time of the trade.
	 */
	SettledEntityMatrix getSettledEntityMatrix();
	/**
	 * Index Factor is the index version factor or percent, expressed as an absolute decimal value between 0 and 1, that multiplied by the original notional amount yields the notional amount covered by the seller of protection.
	 */
	BigDecimal getIndexFactor();
	/**
	 * Seniority of debt instruments comprising the index.
	 */
	CreditSeniorityEnum getSeniority();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	CreditIndexReferenceInformation build();
	
	CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder toBuilder();
	
	static CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder builder() {
		return new CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CreditIndexReferenceInformation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CreditIndexReferenceInformation> getType() {
		return CreditIndexReferenceInformation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("indexName"), processor, FieldWithMetaString.class, getIndexName());
		processRosetta(path.newSubPath("indexId"), processor, FieldWithMetaString.class, getIndexId());
		processor.processBasic(path.newSubPath("indexSeries"), Integer.class, getIndexSeries(), this);
		processor.processBasic(path.newSubPath("indexAnnexVersion"), Integer.class, getIndexAnnexVersion(), this);
		processor.processBasic(path.newSubPath("indexAnnexDate"), Date.class, getIndexAnnexDate(), this);
		processRosetta(path.newSubPath("indexAnnexSource"), processor, FieldWithMetaIndexAnnexSourceEnum.class, getIndexAnnexSource());
		processRosetta(path.newSubPath("excludedReferenceEntity"), processor, ReferenceInformation.class, getExcludedReferenceEntity());
		processRosetta(path.newSubPath("tranche"), processor, Tranche.class, getTranche());
		processRosetta(path.newSubPath("settledEntityMatrix"), processor, SettledEntityMatrix.class, getSettledEntityMatrix());
		processor.processBasic(path.newSubPath("indexFactor"), BigDecimal.class, getIndexFactor(), this);
		processor.processBasic(path.newSubPath("seniority"), CreditSeniorityEnum.class, getSeniority(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CreditIndexReferenceInformationBuilder extends CreditIndexReferenceInformation, IndexReferenceInformation.IndexReferenceInformationBuilder, RosettaModelObjectBuilder {
		FieldWithMetaIndexAnnexSourceEnum.FieldWithMetaIndexAnnexSourceEnumBuilder getOrCreateIndexAnnexSource();
		FieldWithMetaIndexAnnexSourceEnum.FieldWithMetaIndexAnnexSourceEnumBuilder getIndexAnnexSource();
		ReferenceInformation.ReferenceInformationBuilder getOrCreateExcludedReferenceEntity(int _index);
		List<? extends ReferenceInformation.ReferenceInformationBuilder> getExcludedReferenceEntity();
		Tranche.TrancheBuilder getOrCreateTranche();
		Tranche.TrancheBuilder getTranche();
		SettledEntityMatrix.SettledEntityMatrixBuilder getOrCreateSettledEntityMatrix();
		SettledEntityMatrix.SettledEntityMatrixBuilder getSettledEntityMatrix();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexName(FieldWithMetaString indexName0);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexNameValue(String indexName1);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexId(FieldWithMetaString indexId0);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexId(FieldWithMetaString indexId1, int _idx);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexIdValue(String indexId2);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexIdValue(String indexId3, int _idx);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexId(List<? extends FieldWithMetaString> indexId4);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexId(List<? extends FieldWithMetaString> indexId5);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexIdValue(List<? extends String> indexId6);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexIdValue(List<? extends String> indexId7);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexSeries(Integer indexSeries);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexAnnexVersion(Integer indexAnnexVersion);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexAnnexDate(Date indexAnnexDate);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexAnnexSource(FieldWithMetaIndexAnnexSourceEnum indexAnnexSource0);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexAnnexSourceValue(IndexAnnexSourceEnum indexAnnexSource1);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addExcludedReferenceEntity(ReferenceInformation excludedReferenceEntity0);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addExcludedReferenceEntity(ReferenceInformation excludedReferenceEntity1, int _idx);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addExcludedReferenceEntity(List<? extends ReferenceInformation> excludedReferenceEntity2);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setExcludedReferenceEntity(List<? extends ReferenceInformation> excludedReferenceEntity3);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setTranche(Tranche tranche);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setSettledEntityMatrix(SettledEntityMatrix settledEntityMatrix);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexFactor(BigDecimal indexFactor);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setSeniority(CreditSeniorityEnum seniority);
		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("indexName"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIndexName());
			processRosetta(path.newSubPath("indexId"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIndexId());
			processor.processBasic(path.newSubPath("indexSeries"), Integer.class, getIndexSeries(), this);
			processor.processBasic(path.newSubPath("indexAnnexVersion"), Integer.class, getIndexAnnexVersion(), this);
			processor.processBasic(path.newSubPath("indexAnnexDate"), Date.class, getIndexAnnexDate(), this);
			processRosetta(path.newSubPath("indexAnnexSource"), processor, FieldWithMetaIndexAnnexSourceEnum.FieldWithMetaIndexAnnexSourceEnumBuilder.class, getIndexAnnexSource());
			processRosetta(path.newSubPath("excludedReferenceEntity"), processor, ReferenceInformation.ReferenceInformationBuilder.class, getExcludedReferenceEntity());
			processRosetta(path.newSubPath("tranche"), processor, Tranche.TrancheBuilder.class, getTranche());
			processRosetta(path.newSubPath("settledEntityMatrix"), processor, SettledEntityMatrix.SettledEntityMatrixBuilder.class, getSettledEntityMatrix());
			processor.processBasic(path.newSubPath("indexFactor"), BigDecimal.class, getIndexFactor(), this);
			processor.processBasic(path.newSubPath("seniority"), CreditSeniorityEnum.class, getSeniority(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder prune();
	}

	/*********************** Immutable Implementation of CreditIndexReferenceInformation  ***********************/
	class CreditIndexReferenceInformationImpl extends IndexReferenceInformation.IndexReferenceInformationImpl implements CreditIndexReferenceInformation {
		private final Integer indexSeries;
		private final Integer indexAnnexVersion;
		private final Date indexAnnexDate;
		private final FieldWithMetaIndexAnnexSourceEnum indexAnnexSource;
		private final List<? extends ReferenceInformation> excludedReferenceEntity;
		private final Tranche tranche;
		private final SettledEntityMatrix settledEntityMatrix;
		private final BigDecimal indexFactor;
		private final CreditSeniorityEnum seniority;
		private final MetaFields meta;
		
		protected CreditIndexReferenceInformationImpl(CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder builder) {
			super(builder);
			this.indexSeries = builder.getIndexSeries();
			this.indexAnnexVersion = builder.getIndexAnnexVersion();
			this.indexAnnexDate = builder.getIndexAnnexDate();
			this.indexAnnexSource = ofNullable(builder.getIndexAnnexSource()).map(f->f.build()).orElse(null);
			this.excludedReferenceEntity = ofNullable(builder.getExcludedReferenceEntity()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.tranche = ofNullable(builder.getTranche()).map(f->f.build()).orElse(null);
			this.settledEntityMatrix = ofNullable(builder.getSettledEntityMatrix()).map(f->f.build()).orElse(null);
			this.indexFactor = builder.getIndexFactor();
			this.seniority = builder.getSeniority();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("indexSeries")
		public Integer getIndexSeries() {
			return indexSeries;
		}
		
		@Override
		@RosettaAttribute("indexAnnexVersion")
		public Integer getIndexAnnexVersion() {
			return indexAnnexVersion;
		}
		
		@Override
		@RosettaAttribute("indexAnnexDate")
		public Date getIndexAnnexDate() {
			return indexAnnexDate;
		}
		
		@Override
		@RosettaAttribute("indexAnnexSource")
		public FieldWithMetaIndexAnnexSourceEnum getIndexAnnexSource() {
			return indexAnnexSource;
		}
		
		@Override
		@RosettaAttribute("excludedReferenceEntity")
		public List<? extends ReferenceInformation> getExcludedReferenceEntity() {
			return excludedReferenceEntity;
		}
		
		@Override
		@RosettaAttribute("tranche")
		public Tranche getTranche() {
			return tranche;
		}
		
		@Override
		@RosettaAttribute("settledEntityMatrix")
		public SettledEntityMatrix getSettledEntityMatrix() {
			return settledEntityMatrix;
		}
		
		@Override
		@RosettaAttribute("indexFactor")
		public BigDecimal getIndexFactor() {
			return indexFactor;
		}
		
		@Override
		@RosettaAttribute("seniority")
		public CreditSeniorityEnum getSeniority() {
			return seniority;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public CreditIndexReferenceInformation build() {
			return this;
		}
		
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder toBuilder() {
			CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getIndexSeries()).ifPresent(builder::setIndexSeries);
			ofNullable(getIndexAnnexVersion()).ifPresent(builder::setIndexAnnexVersion);
			ofNullable(getIndexAnnexDate()).ifPresent(builder::setIndexAnnexDate);
			ofNullable(getIndexAnnexSource()).ifPresent(builder::setIndexAnnexSource);
			ofNullable(getExcludedReferenceEntity()).ifPresent(builder::setExcludedReferenceEntity);
			ofNullable(getTranche()).ifPresent(builder::setTranche);
			ofNullable(getSettledEntityMatrix()).ifPresent(builder::setSettledEntityMatrix);
			ofNullable(getIndexFactor()).ifPresent(builder::setIndexFactor);
			ofNullable(getSeniority()).ifPresent(builder::setSeniority);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CreditIndexReferenceInformation _that = getType().cast(o);
		
			if (!Objects.equals(indexSeries, _that.getIndexSeries())) return false;
			if (!Objects.equals(indexAnnexVersion, _that.getIndexAnnexVersion())) return false;
			if (!Objects.equals(indexAnnexDate, _that.getIndexAnnexDate())) return false;
			if (!Objects.equals(indexAnnexSource, _that.getIndexAnnexSource())) return false;
			if (!ListEquals.listEquals(excludedReferenceEntity, _that.getExcludedReferenceEntity())) return false;
			if (!Objects.equals(tranche, _that.getTranche())) return false;
			if (!Objects.equals(settledEntityMatrix, _that.getSettledEntityMatrix())) return false;
			if (!Objects.equals(indexFactor, _that.getIndexFactor())) return false;
			if (!Objects.equals(seniority, _that.getSeniority())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (indexSeries != null ? indexSeries.hashCode() : 0);
			_result = 31 * _result + (indexAnnexVersion != null ? indexAnnexVersion.hashCode() : 0);
			_result = 31 * _result + (indexAnnexDate != null ? indexAnnexDate.hashCode() : 0);
			_result = 31 * _result + (indexAnnexSource != null ? indexAnnexSource.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (excludedReferenceEntity != null ? excludedReferenceEntity.hashCode() : 0);
			_result = 31 * _result + (tranche != null ? tranche.hashCode() : 0);
			_result = 31 * _result + (settledEntityMatrix != null ? settledEntityMatrix.hashCode() : 0);
			_result = 31 * _result + (indexFactor != null ? indexFactor.hashCode() : 0);
			_result = 31 * _result + (seniority != null ? seniority.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditIndexReferenceInformation {" +
				"indexSeries=" + this.indexSeries + ", " +
				"indexAnnexVersion=" + this.indexAnnexVersion + ", " +
				"indexAnnexDate=" + this.indexAnnexDate + ", " +
				"indexAnnexSource=" + this.indexAnnexSource + ", " +
				"excludedReferenceEntity=" + this.excludedReferenceEntity + ", " +
				"tranche=" + this.tranche + ", " +
				"settledEntityMatrix=" + this.settledEntityMatrix + ", " +
				"indexFactor=" + this.indexFactor + ", " +
				"seniority=" + this.seniority + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CreditIndexReferenceInformation  ***********************/
	class CreditIndexReferenceInformationBuilderImpl extends IndexReferenceInformation.IndexReferenceInformationBuilderImpl  implements CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder, GlobalKeyBuilder {
	
		protected Integer indexSeries;
		protected Integer indexAnnexVersion;
		protected Date indexAnnexDate;
		protected FieldWithMetaIndexAnnexSourceEnum.FieldWithMetaIndexAnnexSourceEnumBuilder indexAnnexSource;
		protected List<ReferenceInformation.ReferenceInformationBuilder> excludedReferenceEntity = new ArrayList<>();
		protected Tranche.TrancheBuilder tranche;
		protected SettledEntityMatrix.SettledEntityMatrixBuilder settledEntityMatrix;
		protected BigDecimal indexFactor;
		protected CreditSeniorityEnum seniority;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CreditIndexReferenceInformationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("indexSeries")
		public Integer getIndexSeries() {
			return indexSeries;
		}
		
		@Override
		@RosettaAttribute("indexAnnexVersion")
		public Integer getIndexAnnexVersion() {
			return indexAnnexVersion;
		}
		
		@Override
		@RosettaAttribute("indexAnnexDate")
		public Date getIndexAnnexDate() {
			return indexAnnexDate;
		}
		
		@Override
		@RosettaAttribute("indexAnnexSource")
		public FieldWithMetaIndexAnnexSourceEnum.FieldWithMetaIndexAnnexSourceEnumBuilder getIndexAnnexSource() {
			return indexAnnexSource;
		}
		
		@Override
		public FieldWithMetaIndexAnnexSourceEnum.FieldWithMetaIndexAnnexSourceEnumBuilder getOrCreateIndexAnnexSource() {
			FieldWithMetaIndexAnnexSourceEnum.FieldWithMetaIndexAnnexSourceEnumBuilder result;
			if (indexAnnexSource!=null) {
				result = indexAnnexSource;
			}
			else {
				result = indexAnnexSource = FieldWithMetaIndexAnnexSourceEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("excludedReferenceEntity")
		public List<? extends ReferenceInformation.ReferenceInformationBuilder> getExcludedReferenceEntity() {
			return excludedReferenceEntity;
		}
		
		public ReferenceInformation.ReferenceInformationBuilder getOrCreateExcludedReferenceEntity(int _index) {
		
			if (excludedReferenceEntity==null) {
				this.excludedReferenceEntity = new ArrayList<>();
			}
			ReferenceInformation.ReferenceInformationBuilder result;
			return getIndex(excludedReferenceEntity, _index, () -> {
						ReferenceInformation.ReferenceInformationBuilder newExcludedReferenceEntity = ReferenceInformation.builder();
						return newExcludedReferenceEntity;
					});
		}
		
		@Override
		@RosettaAttribute("tranche")
		public Tranche.TrancheBuilder getTranche() {
			return tranche;
		}
		
		@Override
		public Tranche.TrancheBuilder getOrCreateTranche() {
			Tranche.TrancheBuilder result;
			if (tranche!=null) {
				result = tranche;
			}
			else {
				result = tranche = Tranche.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settledEntityMatrix")
		public SettledEntityMatrix.SettledEntityMatrixBuilder getSettledEntityMatrix() {
			return settledEntityMatrix;
		}
		
		@Override
		public SettledEntityMatrix.SettledEntityMatrixBuilder getOrCreateSettledEntityMatrix() {
			SettledEntityMatrix.SettledEntityMatrixBuilder result;
			if (settledEntityMatrix!=null) {
				result = settledEntityMatrix;
			}
			else {
				result = settledEntityMatrix = SettledEntityMatrix.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("indexFactor")
		public BigDecimal getIndexFactor() {
			return indexFactor;
		}
		
		@Override
		@RosettaAttribute("seniority")
		public CreditSeniorityEnum getSeniority() {
			return seniority;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("indexName")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexName(FieldWithMetaString indexName) {
			this.indexName = indexName==null?null:indexName.toBuilder();
			return this;
		}
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexNameValue(String indexName) {
			this.getOrCreateIndexName().setValue(indexName);
			return this;
		}
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexId(FieldWithMetaString indexId) {
			if (indexId!=null) this.indexId.add(indexId.toBuilder());
			return this;
		}
		
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexId(FieldWithMetaString indexId, int _idx) {
			getIndex(this.indexId, _idx, () -> indexId.toBuilder());
			return this;
		}
		
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexIdValue(String indexId) {
			this.getOrCreateIndexId(-1).setValue(indexId);
			return this;
		}
		
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexIdValue(String indexId, int _idx) {
			this.getOrCreateIndexId(_idx).setValue(indexId);
			return this;
		}
		@Override 
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexId(List<? extends FieldWithMetaString> indexIds) {
			if (indexIds != null) {
				for (FieldWithMetaString toAdd : indexIds) {
					this.indexId.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("indexId")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexId(List<? extends FieldWithMetaString> indexIds) {
			if (indexIds == null)  {
				this.indexId = new ArrayList<>();
			}
			else {
				this.indexId = indexIds.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addIndexIdValue(List<? extends String> indexIds) {
			if (indexIds != null) {
				for (String toAdd : indexIds) {
					this.addIndexIdValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexIdValue(List<? extends String> indexIds) {
			this.indexId.clear();
			if (indexIds!=null) {
				indexIds.forEach(this::addIndexIdValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("indexSeries")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexSeries(Integer indexSeries) {
			this.indexSeries = indexSeries==null?null:indexSeries;
			return this;
		}
		@Override
		@RosettaAttribute("indexAnnexVersion")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexAnnexVersion(Integer indexAnnexVersion) {
			this.indexAnnexVersion = indexAnnexVersion==null?null:indexAnnexVersion;
			return this;
		}
		@Override
		@RosettaAttribute("indexAnnexDate")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexAnnexDate(Date indexAnnexDate) {
			this.indexAnnexDate = indexAnnexDate==null?null:indexAnnexDate;
			return this;
		}
		@Override
		@RosettaAttribute("indexAnnexSource")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexAnnexSource(FieldWithMetaIndexAnnexSourceEnum indexAnnexSource) {
			this.indexAnnexSource = indexAnnexSource==null?null:indexAnnexSource.toBuilder();
			return this;
		}
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexAnnexSourceValue(IndexAnnexSourceEnum indexAnnexSource) {
			this.getOrCreateIndexAnnexSource().setValue(indexAnnexSource);
			return this;
		}
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addExcludedReferenceEntity(ReferenceInformation excludedReferenceEntity) {
			if (excludedReferenceEntity!=null) this.excludedReferenceEntity.add(excludedReferenceEntity.toBuilder());
			return this;
		}
		
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addExcludedReferenceEntity(ReferenceInformation excludedReferenceEntity, int _idx) {
			getIndex(this.excludedReferenceEntity, _idx, () -> excludedReferenceEntity.toBuilder());
			return this;
		}
		@Override 
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder addExcludedReferenceEntity(List<? extends ReferenceInformation> excludedReferenceEntitys) {
			if (excludedReferenceEntitys != null) {
				for (ReferenceInformation toAdd : excludedReferenceEntitys) {
					this.excludedReferenceEntity.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("excludedReferenceEntity")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setExcludedReferenceEntity(List<? extends ReferenceInformation> excludedReferenceEntitys) {
			if (excludedReferenceEntitys == null)  {
				this.excludedReferenceEntity = new ArrayList<>();
			}
			else {
				this.excludedReferenceEntity = excludedReferenceEntitys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("tranche")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setTranche(Tranche tranche) {
			this.tranche = tranche==null?null:tranche.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settledEntityMatrix")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setSettledEntityMatrix(SettledEntityMatrix settledEntityMatrix) {
			this.settledEntityMatrix = settledEntityMatrix==null?null:settledEntityMatrix.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("indexFactor")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setIndexFactor(BigDecimal indexFactor) {
			this.indexFactor = indexFactor==null?null:indexFactor;
			return this;
		}
		@Override
		@RosettaAttribute("seniority")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setSeniority(CreditSeniorityEnum seniority) {
			this.seniority = seniority==null?null:seniority;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public CreditIndexReferenceInformation build() {
			return new CreditIndexReferenceInformation.CreditIndexReferenceInformationImpl(this);
		}
		
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder prune() {
			super.prune();
			if (indexAnnexSource!=null && !indexAnnexSource.prune().hasData()) indexAnnexSource = null;
			excludedReferenceEntity = excludedReferenceEntity.stream().filter(b->b!=null).<ReferenceInformation.ReferenceInformationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (tranche!=null && !tranche.prune().hasData()) tranche = null;
			if (settledEntityMatrix!=null && !settledEntityMatrix.prune().hasData()) settledEntityMatrix = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getIndexSeries()!=null) return true;
			if (getIndexAnnexVersion()!=null) return true;
			if (getIndexAnnexDate()!=null) return true;
			if (getIndexAnnexSource()!=null) return true;
			if (getExcludedReferenceEntity()!=null && getExcludedReferenceEntity().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getTranche()!=null && getTranche().hasData()) return true;
			if (getSettledEntityMatrix()!=null && getSettledEntityMatrix().hasData()) return true;
			if (getIndexFactor()!=null) return true;
			if (getSeniority()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder o = (CreditIndexReferenceInformation.CreditIndexReferenceInformationBuilder) other;
			
			merger.mergeRosetta(getIndexAnnexSource(), o.getIndexAnnexSource(), this::setIndexAnnexSource);
			merger.mergeRosetta(getExcludedReferenceEntity(), o.getExcludedReferenceEntity(), this::getOrCreateExcludedReferenceEntity);
			merger.mergeRosetta(getTranche(), o.getTranche(), this::setTranche);
			merger.mergeRosetta(getSettledEntityMatrix(), o.getSettledEntityMatrix(), this::setSettledEntityMatrix);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getIndexSeries(), o.getIndexSeries(), this::setIndexSeries);
			merger.mergeBasic(getIndexAnnexVersion(), o.getIndexAnnexVersion(), this::setIndexAnnexVersion);
			merger.mergeBasic(getIndexAnnexDate(), o.getIndexAnnexDate(), this::setIndexAnnexDate);
			merger.mergeBasic(getIndexFactor(), o.getIndexFactor(), this::setIndexFactor);
			merger.mergeBasic(getSeniority(), o.getSeniority(), this::setSeniority);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CreditIndexReferenceInformation _that = getType().cast(o);
		
			if (!Objects.equals(indexSeries, _that.getIndexSeries())) return false;
			if (!Objects.equals(indexAnnexVersion, _that.getIndexAnnexVersion())) return false;
			if (!Objects.equals(indexAnnexDate, _that.getIndexAnnexDate())) return false;
			if (!Objects.equals(indexAnnexSource, _that.getIndexAnnexSource())) return false;
			if (!ListEquals.listEquals(excludedReferenceEntity, _that.getExcludedReferenceEntity())) return false;
			if (!Objects.equals(tranche, _that.getTranche())) return false;
			if (!Objects.equals(settledEntityMatrix, _that.getSettledEntityMatrix())) return false;
			if (!Objects.equals(indexFactor, _that.getIndexFactor())) return false;
			if (!Objects.equals(seniority, _that.getSeniority())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (indexSeries != null ? indexSeries.hashCode() : 0);
			_result = 31 * _result + (indexAnnexVersion != null ? indexAnnexVersion.hashCode() : 0);
			_result = 31 * _result + (indexAnnexDate != null ? indexAnnexDate.hashCode() : 0);
			_result = 31 * _result + (indexAnnexSource != null ? indexAnnexSource.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (excludedReferenceEntity != null ? excludedReferenceEntity.hashCode() : 0);
			_result = 31 * _result + (tranche != null ? tranche.hashCode() : 0);
			_result = 31 * _result + (settledEntityMatrix != null ? settledEntityMatrix.hashCode() : 0);
			_result = 31 * _result + (indexFactor != null ? indexFactor.hashCode() : 0);
			_result = 31 * _result + (seniority != null ? seniority.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditIndexReferenceInformationBuilder {" +
				"indexSeries=" + this.indexSeries + ", " +
				"indexAnnexVersion=" + this.indexAnnexVersion + ", " +
				"indexAnnexDate=" + this.indexAnnexDate + ", " +
				"indexAnnexSource=" + this.indexAnnexSource + ", " +
				"excludedReferenceEntity=" + this.excludedReferenceEntity + ", " +
				"tranche=" + this.tranche + ", " +
				"settledEntityMatrix=" + this.settledEntityMatrix + ", " +
				"indexFactor=" + this.indexFactor + ", " +
				"seniority=" + this.seniority + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
