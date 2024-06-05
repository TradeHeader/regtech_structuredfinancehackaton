package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.IndexReferenceInformation;
import cdm.base.staticdata.asset.common.IndexReferenceInformation.IndexReferenceInformationBuilder;
import cdm.base.staticdata.asset.common.IndexReferenceInformation.IndexReferenceInformationBuilderImpl;
import cdm.base.staticdata.asset.common.IndexReferenceInformation.IndexReferenceInformationImpl;
import cdm.base.staticdata.asset.common.meta.IndexReferenceInformationMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class defining information related to Index
 * @version ${project.version}
 */
@RosettaDataType(value="IndexReferenceInformation", builder=IndexReferenceInformation.IndexReferenceInformationBuilderImpl.class, version="${project.version}")
public interface IndexReferenceInformation extends RosettaModelObject {

	IndexReferenceInformationMeta metaData = new IndexReferenceInformationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The name of the index expressed as a free format string with an associated scheme.
	 */
	FieldWithMetaString getIndexName();
	/**
	 * An index identifier (e.g. RED pair code).
	 */
	List<? extends FieldWithMetaString> getIndexId();

	/*********************** Build Methods  ***********************/
	IndexReferenceInformation build();
	
	IndexReferenceInformation.IndexReferenceInformationBuilder toBuilder();
	
	static IndexReferenceInformation.IndexReferenceInformationBuilder builder() {
		return new IndexReferenceInformation.IndexReferenceInformationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends IndexReferenceInformation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends IndexReferenceInformation> getType() {
		return IndexReferenceInformation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("indexName"), processor, FieldWithMetaString.class, getIndexName());
		processRosetta(path.newSubPath("indexId"), processor, FieldWithMetaString.class, getIndexId());
	}
	

	/*********************** Builder Interface  ***********************/
	interface IndexReferenceInformationBuilder extends IndexReferenceInformation, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIndexName();
		FieldWithMetaString.FieldWithMetaStringBuilder getIndexName();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIndexId(int _index);
		List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getIndexId();
		IndexReferenceInformation.IndexReferenceInformationBuilder setIndexName(FieldWithMetaString indexName0);
		IndexReferenceInformation.IndexReferenceInformationBuilder setIndexNameValue(String indexName1);
		IndexReferenceInformation.IndexReferenceInformationBuilder addIndexId(FieldWithMetaString indexId0);
		IndexReferenceInformation.IndexReferenceInformationBuilder addIndexId(FieldWithMetaString indexId1, int _idx);
		IndexReferenceInformation.IndexReferenceInformationBuilder addIndexIdValue(String indexId2);
		IndexReferenceInformation.IndexReferenceInformationBuilder addIndexIdValue(String indexId3, int _idx);
		IndexReferenceInformation.IndexReferenceInformationBuilder addIndexId(List<? extends FieldWithMetaString> indexId4);
		IndexReferenceInformation.IndexReferenceInformationBuilder setIndexId(List<? extends FieldWithMetaString> indexId5);
		IndexReferenceInformation.IndexReferenceInformationBuilder addIndexIdValue(List<? extends String> indexId6);
		IndexReferenceInformation.IndexReferenceInformationBuilder setIndexIdValue(List<? extends String> indexId7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("indexName"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIndexName());
			processRosetta(path.newSubPath("indexId"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIndexId());
		}
		

		IndexReferenceInformation.IndexReferenceInformationBuilder prune();
	}

	/*********************** Immutable Implementation of IndexReferenceInformation  ***********************/
	class IndexReferenceInformationImpl implements IndexReferenceInformation {
		private final FieldWithMetaString indexName;
		private final List<? extends FieldWithMetaString> indexId;
		
		protected IndexReferenceInformationImpl(IndexReferenceInformation.IndexReferenceInformationBuilder builder) {
			this.indexName = ofNullable(builder.getIndexName()).map(f->f.build()).orElse(null);
			this.indexId = ofNullable(builder.getIndexId()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("indexName")
		public FieldWithMetaString getIndexName() {
			return indexName;
		}
		
		@Override
		@RosettaAttribute("indexId")
		public List<? extends FieldWithMetaString> getIndexId() {
			return indexId;
		}
		
		@Override
		public IndexReferenceInformation build() {
			return this;
		}
		
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder toBuilder() {
			IndexReferenceInformation.IndexReferenceInformationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(IndexReferenceInformation.IndexReferenceInformationBuilder builder) {
			ofNullable(getIndexName()).ifPresent(builder::setIndexName);
			ofNullable(getIndexId()).ifPresent(builder::setIndexId);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			IndexReferenceInformation _that = getType().cast(o);
		
			if (!Objects.equals(indexName, _that.getIndexName())) return false;
			if (!ListEquals.listEquals(indexId, _that.getIndexId())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (indexName != null ? indexName.hashCode() : 0);
			_result = 31 * _result + (indexId != null ? indexId.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IndexReferenceInformation {" +
				"indexName=" + this.indexName + ", " +
				"indexId=" + this.indexId +
			'}';
		}
	}

	/*********************** Builder Implementation of IndexReferenceInformation  ***********************/
	class IndexReferenceInformationBuilderImpl implements IndexReferenceInformation.IndexReferenceInformationBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder indexName;
		protected List<FieldWithMetaString.FieldWithMetaStringBuilder> indexId = new ArrayList<>();
	
		public IndexReferenceInformationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("indexName")
		public FieldWithMetaString.FieldWithMetaStringBuilder getIndexName() {
			return indexName;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIndexName() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (indexName!=null) {
				result = indexName;
			}
			else {
				result = indexName = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("indexId")
		public List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getIndexId() {
			return indexId;
		}
		
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateIndexId(int _index) {
		
			if (indexId==null) {
				this.indexId = new ArrayList<>();
			}
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			return getIndex(indexId, _index, () -> {
						FieldWithMetaString.FieldWithMetaStringBuilder newIndexId = FieldWithMetaString.builder();
						return newIndexId;
					});
		}
		
	
		@Override
		@RosettaAttribute("indexName")
		public IndexReferenceInformation.IndexReferenceInformationBuilder setIndexName(FieldWithMetaString indexName) {
			this.indexName = indexName==null?null:indexName.toBuilder();
			return this;
		}
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder setIndexNameValue(String indexName) {
			this.getOrCreateIndexName().setValue(indexName);
			return this;
		}
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder addIndexId(FieldWithMetaString indexId) {
			if (indexId!=null) this.indexId.add(indexId.toBuilder());
			return this;
		}
		
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder addIndexId(FieldWithMetaString indexId, int _idx) {
			getIndex(this.indexId, _idx, () -> indexId.toBuilder());
			return this;
		}
		
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder addIndexIdValue(String indexId) {
			this.getOrCreateIndexId(-1).setValue(indexId);
			return this;
		}
		
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder addIndexIdValue(String indexId, int _idx) {
			this.getOrCreateIndexId(_idx).setValue(indexId);
			return this;
		}
		@Override 
		public IndexReferenceInformation.IndexReferenceInformationBuilder addIndexId(List<? extends FieldWithMetaString> indexIds) {
			if (indexIds != null) {
				for (FieldWithMetaString toAdd : indexIds) {
					this.indexId.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("indexId")
		public IndexReferenceInformation.IndexReferenceInformationBuilder setIndexId(List<? extends FieldWithMetaString> indexIds) {
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
		public IndexReferenceInformation.IndexReferenceInformationBuilder addIndexIdValue(List<? extends String> indexIds) {
			if (indexIds != null) {
				for (String toAdd : indexIds) {
					this.addIndexIdValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder setIndexIdValue(List<? extends String> indexIds) {
			this.indexId.clear();
			if (indexIds!=null) {
				indexIds.forEach(this::addIndexIdValue);
			}
			return this;
		}
		
		
		@Override
		public IndexReferenceInformation build() {
			return new IndexReferenceInformation.IndexReferenceInformationImpl(this);
		}
		
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder prune() {
			if (indexName!=null && !indexName.prune().hasData()) indexName = null;
			indexId = indexId.stream().filter(b->b!=null).<FieldWithMetaString.FieldWithMetaStringBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIndexName()!=null) return true;
			if (getIndexId()!=null && !getIndexId().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			IndexReferenceInformation.IndexReferenceInformationBuilder o = (IndexReferenceInformation.IndexReferenceInformationBuilder) other;
			
			merger.mergeRosetta(getIndexName(), o.getIndexName(), this::setIndexName);
			merger.mergeRosetta(getIndexId(), o.getIndexId(), this::getOrCreateIndexId);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			IndexReferenceInformation _that = getType().cast(o);
		
			if (!Objects.equals(indexName, _that.getIndexName())) return false;
			if (!ListEquals.listEquals(indexId, _that.getIndexId())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (indexName != null ? indexName.hashCode() : 0);
			_result = 31 * _result + (indexId != null ? indexId.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IndexReferenceInformationBuilder {" +
				"indexName=" + this.indexName + ", " +
				"indexId=" + this.indexId +
			'}';
		}
	}
}
