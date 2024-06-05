package cdm.observable.event;

import cdm.observable.event.PubliclyAvailableInformation;
import cdm.observable.event.PubliclyAvailableInformation.PubliclyAvailableInformationBuilder;
import cdm.observable.event.PubliclyAvailableInformation.PubliclyAvailableInformationBuilderImpl;
import cdm.observable.event.PubliclyAvailableInformation.PubliclyAvailableInformationImpl;
import cdm.observable.event.meta.PubliclyAvailableInformationMeta;
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
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="PubliclyAvailableInformation", builder=PubliclyAvailableInformation.PubliclyAvailableInformationBuilderImpl.class, version="${project.version}")
public interface PubliclyAvailableInformation extends RosettaModelObject {

	PubliclyAvailableInformationMeta metaData = new PubliclyAvailableInformationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * If this element is specified and set to &#39;true&#39;, indicates that ISDA defined Standard Public Sources are applicable.
	 */
	Boolean getStandardPublicSources();
	/**
	 * A public information source, e.g. a particular newspaper or electronic news service, that may publish relevant information used in the determination of whether or not a credit event has occurred. ISDA 2003 Term: Public Source.
	 */
	List<String> getPublicSource();
	/**
	 * The minimum number of the specified public information sources that must publish information that reasonably confirms that a credit event has occurred. The market convention is two. ISDA 2003 Term: Specified Number.
	 */
	Integer getSpecifiedNumber();

	/*********************** Build Methods  ***********************/
	PubliclyAvailableInformation build();
	
	PubliclyAvailableInformation.PubliclyAvailableInformationBuilder toBuilder();
	
	static PubliclyAvailableInformation.PubliclyAvailableInformationBuilder builder() {
		return new PubliclyAvailableInformation.PubliclyAvailableInformationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PubliclyAvailableInformation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PubliclyAvailableInformation> getType() {
		return PubliclyAvailableInformation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("standardPublicSources"), Boolean.class, getStandardPublicSources(), this);
		processor.processBasic(path.newSubPath("publicSource"), String.class, getPublicSource(), this);
		processor.processBasic(path.newSubPath("specifiedNumber"), Integer.class, getSpecifiedNumber(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PubliclyAvailableInformationBuilder extends PubliclyAvailableInformation, RosettaModelObjectBuilder {
		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder setStandardPublicSources(Boolean standardPublicSources);
		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder addPublicSource(String publicSource0);
		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder addPublicSource(String publicSource1, int _idx);
		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder addPublicSource(List<? extends String> publicSource2);
		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder setPublicSource(List<? extends String> publicSource3);
		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder setSpecifiedNumber(Integer specifiedNumber);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("standardPublicSources"), Boolean.class, getStandardPublicSources(), this);
			processor.processBasic(path.newSubPath("publicSource"), String.class, getPublicSource(), this);
			processor.processBasic(path.newSubPath("specifiedNumber"), Integer.class, getSpecifiedNumber(), this);
		}
		

		PubliclyAvailableInformation.PubliclyAvailableInformationBuilder prune();
	}

	/*********************** Immutable Implementation of PubliclyAvailableInformation  ***********************/
	class PubliclyAvailableInformationImpl implements PubliclyAvailableInformation {
		private final Boolean standardPublicSources;
		private final List<String> publicSource;
		private final Integer specifiedNumber;
		
		protected PubliclyAvailableInformationImpl(PubliclyAvailableInformation.PubliclyAvailableInformationBuilder builder) {
			this.standardPublicSources = builder.getStandardPublicSources();
			this.publicSource = ofNullable(builder.getPublicSource()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.specifiedNumber = builder.getSpecifiedNumber();
		}
		
		@Override
		@RosettaAttribute("standardPublicSources")
		public Boolean getStandardPublicSources() {
			return standardPublicSources;
		}
		
		@Override
		@RosettaAttribute("publicSource")
		public List<String> getPublicSource() {
			return publicSource;
		}
		
		@Override
		@RosettaAttribute("specifiedNumber")
		public Integer getSpecifiedNumber() {
			return specifiedNumber;
		}
		
		@Override
		public PubliclyAvailableInformation build() {
			return this;
		}
		
		@Override
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder toBuilder() {
			PubliclyAvailableInformation.PubliclyAvailableInformationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PubliclyAvailableInformation.PubliclyAvailableInformationBuilder builder) {
			ofNullable(getStandardPublicSources()).ifPresent(builder::setStandardPublicSources);
			ofNullable(getPublicSource()).ifPresent(builder::setPublicSource);
			ofNullable(getSpecifiedNumber()).ifPresent(builder::setSpecifiedNumber);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PubliclyAvailableInformation _that = getType().cast(o);
		
			if (!Objects.equals(standardPublicSources, _that.getStandardPublicSources())) return false;
			if (!ListEquals.listEquals(publicSource, _that.getPublicSource())) return false;
			if (!Objects.equals(specifiedNumber, _that.getSpecifiedNumber())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (standardPublicSources != null ? standardPublicSources.hashCode() : 0);
			_result = 31 * _result + (publicSource != null ? publicSource.hashCode() : 0);
			_result = 31 * _result + (specifiedNumber != null ? specifiedNumber.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PubliclyAvailableInformation {" +
				"standardPublicSources=" + this.standardPublicSources + ", " +
				"publicSource=" + this.publicSource + ", " +
				"specifiedNumber=" + this.specifiedNumber +
			'}';
		}
	}

	/*********************** Builder Implementation of PubliclyAvailableInformation  ***********************/
	class PubliclyAvailableInformationBuilderImpl implements PubliclyAvailableInformation.PubliclyAvailableInformationBuilder {
	
		protected Boolean standardPublicSources;
		protected List<String> publicSource = new ArrayList<>();
		protected Integer specifiedNumber;
	
		public PubliclyAvailableInformationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("standardPublicSources")
		public Boolean getStandardPublicSources() {
			return standardPublicSources;
		}
		
		@Override
		@RosettaAttribute("publicSource")
		public List<String> getPublicSource() {
			return publicSource;
		}
		
		@Override
		@RosettaAttribute("specifiedNumber")
		public Integer getSpecifiedNumber() {
			return specifiedNumber;
		}
		
	
		@Override
		@RosettaAttribute("standardPublicSources")
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder setStandardPublicSources(Boolean standardPublicSources) {
			this.standardPublicSources = standardPublicSources==null?null:standardPublicSources;
			return this;
		}
		@Override
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder addPublicSource(String publicSource) {
			if (publicSource!=null) this.publicSource.add(publicSource);
			return this;
		}
		
		@Override
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder addPublicSource(String publicSource, int _idx) {
			getIndex(this.publicSource, _idx, () -> publicSource);
			return this;
		}
		@Override 
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder addPublicSource(List<? extends String> publicSources) {
			if (publicSources != null) {
				for (String toAdd : publicSources) {
					this.publicSource.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("publicSource")
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder setPublicSource(List<? extends String> publicSources) {
			if (publicSources == null)  {
				this.publicSource = new ArrayList<>();
			}
			else {
				this.publicSource = publicSources.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("specifiedNumber")
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder setSpecifiedNumber(Integer specifiedNumber) {
			this.specifiedNumber = specifiedNumber==null?null:specifiedNumber;
			return this;
		}
		
		@Override
		public PubliclyAvailableInformation build() {
			return new PubliclyAvailableInformation.PubliclyAvailableInformationImpl(this);
		}
		
		@Override
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStandardPublicSources()!=null) return true;
			if (getPublicSource()!=null && !getPublicSource().isEmpty()) return true;
			if (getSpecifiedNumber()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PubliclyAvailableInformation.PubliclyAvailableInformationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PubliclyAvailableInformation.PubliclyAvailableInformationBuilder o = (PubliclyAvailableInformation.PubliclyAvailableInformationBuilder) other;
			
			
			merger.mergeBasic(getStandardPublicSources(), o.getStandardPublicSources(), this::setStandardPublicSources);
			merger.mergeBasic(getPublicSource(), o.getPublicSource(), (Consumer<String>) this::addPublicSource);
			merger.mergeBasic(getSpecifiedNumber(), o.getSpecifiedNumber(), this::setSpecifiedNumber);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PubliclyAvailableInformation _that = getType().cast(o);
		
			if (!Objects.equals(standardPublicSources, _that.getStandardPublicSources())) return false;
			if (!ListEquals.listEquals(publicSource, _that.getPublicSource())) return false;
			if (!Objects.equals(specifiedNumber, _that.getSpecifiedNumber())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (standardPublicSources != null ? standardPublicSources.hashCode() : 0);
			_result = 31 * _result + (publicSource != null ? publicSource.hashCode() : 0);
			_result = 31 * _result + (specifiedNumber != null ? specifiedNumber.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PubliclyAvailableInformationBuilder {" +
				"standardPublicSources=" + this.standardPublicSources + ", " +
				"publicSource=" + this.publicSource + ", " +
				"specifiedNumber=" + this.specifiedNumber +
			'}';
		}
	}
}
