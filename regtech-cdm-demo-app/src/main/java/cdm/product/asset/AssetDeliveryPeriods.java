package cdm.product.asset;

import cdm.product.asset.AssetDeliveryPeriods;
import cdm.product.asset.AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder;
import cdm.product.asset.AssetDeliveryPeriods.AssetDeliveryPeriodsBuilderImpl;
import cdm.product.asset.AssetDeliveryPeriods.AssetDeliveryPeriodsImpl;
import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.meta.AssetDeliveryPeriodsMeta;
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
import com.rosetta.model.lib.records.Date;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Defines the periods of delivery, including the delivery profile.
 * @version ${project.version}
 */
@RosettaDataType(value="AssetDeliveryPeriods", builder=AssetDeliveryPeriods.AssetDeliveryPeriodsBuilderImpl.class, version="${project.version}")
public interface AssetDeliveryPeriods extends RosettaModelObject {

	AssetDeliveryPeriodsMeta metaData = new AssetDeliveryPeriodsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines the delivery profile of the asset, including the load type and the delivery intervals.
	 */
	List<? extends AssetDeliveryProfile> getProfile();
	/**
	 * Delivery start date
	 */
	Date getStartDate();
	/**
	 * Delivery end date
	 */
	Date getEndDate();

	/*********************** Build Methods  ***********************/
	AssetDeliveryPeriods build();
	
	AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder toBuilder();
	
	static AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder builder() {
		return new AssetDeliveryPeriods.AssetDeliveryPeriodsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AssetDeliveryPeriods> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AssetDeliveryPeriods> getType() {
		return AssetDeliveryPeriods.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("profile"), processor, AssetDeliveryProfile.class, getProfile());
		processor.processBasic(path.newSubPath("startDate"), Date.class, getStartDate(), this);
		processor.processBasic(path.newSubPath("endDate"), Date.class, getEndDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AssetDeliveryPeriodsBuilder extends AssetDeliveryPeriods, RosettaModelObjectBuilder {
		AssetDeliveryProfile.AssetDeliveryProfileBuilder getOrCreateProfile(int _index);
		List<? extends AssetDeliveryProfile.AssetDeliveryProfileBuilder> getProfile();
		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder addProfile(AssetDeliveryProfile profile0);
		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder addProfile(AssetDeliveryProfile profile1, int _idx);
		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder addProfile(List<? extends AssetDeliveryProfile> profile2);
		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder setProfile(List<? extends AssetDeliveryProfile> profile3);
		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder setStartDate(Date startDate);
		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder setEndDate(Date endDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("profile"), processor, AssetDeliveryProfile.AssetDeliveryProfileBuilder.class, getProfile());
			processor.processBasic(path.newSubPath("startDate"), Date.class, getStartDate(), this);
			processor.processBasic(path.newSubPath("endDate"), Date.class, getEndDate(), this);
		}
		

		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder prune();
	}

	/*********************** Immutable Implementation of AssetDeliveryPeriods  ***********************/
	class AssetDeliveryPeriodsImpl implements AssetDeliveryPeriods {
		private final List<? extends AssetDeliveryProfile> profile;
		private final Date startDate;
		private final Date endDate;
		
		protected AssetDeliveryPeriodsImpl(AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder builder) {
			this.profile = ofNullable(builder.getProfile()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.startDate = builder.getStartDate();
			this.endDate = builder.getEndDate();
		}
		
		@Override
		@RosettaAttribute("profile")
		public List<? extends AssetDeliveryProfile> getProfile() {
			return profile;
		}
		
		@Override
		@RosettaAttribute("startDate")
		public Date getStartDate() {
			return startDate;
		}
		
		@Override
		@RosettaAttribute("endDate")
		public Date getEndDate() {
			return endDate;
		}
		
		@Override
		public AssetDeliveryPeriods build() {
			return this;
		}
		
		@Override
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder toBuilder() {
			AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder builder) {
			ofNullable(getProfile()).ifPresent(builder::setProfile);
			ofNullable(getStartDate()).ifPresent(builder::setStartDate);
			ofNullable(getEndDate()).ifPresent(builder::setEndDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetDeliveryPeriods _that = getType().cast(o);
		
			if (!ListEquals.listEquals(profile, _that.getProfile())) return false;
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (profile != null ? profile.hashCode() : 0);
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetDeliveryPeriods {" +
				"profile=" + this.profile + ", " +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate +
			'}';
		}
	}

	/*********************** Builder Implementation of AssetDeliveryPeriods  ***********************/
	class AssetDeliveryPeriodsBuilderImpl implements AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder {
	
		protected List<AssetDeliveryProfile.AssetDeliveryProfileBuilder> profile = new ArrayList<>();
		protected Date startDate;
		protected Date endDate;
	
		public AssetDeliveryPeriodsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("profile")
		public List<? extends AssetDeliveryProfile.AssetDeliveryProfileBuilder> getProfile() {
			return profile;
		}
		
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder getOrCreateProfile(int _index) {
		
			if (profile==null) {
				this.profile = new ArrayList<>();
			}
			AssetDeliveryProfile.AssetDeliveryProfileBuilder result;
			return getIndex(profile, _index, () -> {
						AssetDeliveryProfile.AssetDeliveryProfileBuilder newProfile = AssetDeliveryProfile.builder();
						return newProfile;
					});
		}
		
		@Override
		@RosettaAttribute("startDate")
		public Date getStartDate() {
			return startDate;
		}
		
		@Override
		@RosettaAttribute("endDate")
		public Date getEndDate() {
			return endDate;
		}
		
	
		@Override
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder addProfile(AssetDeliveryProfile profile) {
			if (profile!=null) this.profile.add(profile.toBuilder());
			return this;
		}
		
		@Override
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder addProfile(AssetDeliveryProfile profile, int _idx) {
			getIndex(this.profile, _idx, () -> profile.toBuilder());
			return this;
		}
		@Override 
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder addProfile(List<? extends AssetDeliveryProfile> profiles) {
			if (profiles != null) {
				for (AssetDeliveryProfile toAdd : profiles) {
					this.profile.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("profile")
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder setProfile(List<? extends AssetDeliveryProfile> profiles) {
			if (profiles == null)  {
				this.profile = new ArrayList<>();
			}
			else {
				this.profile = profiles.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("startDate")
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder setStartDate(Date startDate) {
			this.startDate = startDate==null?null:startDate;
			return this;
		}
		@Override
		@RosettaAttribute("endDate")
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder setEndDate(Date endDate) {
			this.endDate = endDate==null?null:endDate;
			return this;
		}
		
		@Override
		public AssetDeliveryPeriods build() {
			return new AssetDeliveryPeriods.AssetDeliveryPeriodsImpl(this);
		}
		
		@Override
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder prune() {
			profile = profile.stream().filter(b->b!=null).<AssetDeliveryProfile.AssetDeliveryProfileBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getProfile()!=null && getProfile().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getStartDate()!=null) return true;
			if (getEndDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder o = (AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder) other;
			
			merger.mergeRosetta(getProfile(), o.getProfile(), this::getOrCreateProfile);
			
			merger.mergeBasic(getStartDate(), o.getStartDate(), this::setStartDate);
			merger.mergeBasic(getEndDate(), o.getEndDate(), this::setEndDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetDeliveryPeriods _that = getType().cast(o);
		
			if (!ListEquals.listEquals(profile, _that.getProfile())) return false;
			if (!Objects.equals(startDate, _that.getStartDate())) return false;
			if (!Objects.equals(endDate, _that.getEndDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (profile != null ? profile.hashCode() : 0);
			_result = 31 * _result + (startDate != null ? startDate.hashCode() : 0);
			_result = 31 * _result + (endDate != null ? endDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetDeliveryPeriodsBuilder {" +
				"profile=" + this.profile + ", " +
				"startDate=" + this.startDate + ", " +
				"endDate=" + this.endDate +
			'}';
		}
	}
}
